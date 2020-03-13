import configparser
import smtplib
import sys
import re
import time
import calendar

def sendEmail():

    # initialize from properties file
    config = configparser.RawConfigParser()
    config.read('daemon.properties')

    # set mail parameters
    try:
        domain          = config.get('email', 'email.domain')
        port            = config.get('email', 'email.port')
        mailId          = config.get('email', 'email.mailid')
        mailPassword    = sys.argv[1]
        toMailId        = config.get('email', 'email.tomailid')
        subject         = config.get('email', 'email.subject')
        message         = config.get('email', 'email.message')
        signature       = config.get('email', 'email.signature')
    except configparser.NoSectionError:
        print('Check daemon.properties file and email section the file')
        return False

    # Connect to smtp server
    try:
        server = smtplib.SMTP(domain,int(port))
    except smtplib.socket.gaierror:
        print('Socket Error. Please check SMTP server and port details.')
        return False
    except TimeoutError:
        print('Operation timed out. Please check SMTP server and port details.')
        return False

    server.ehlo()
    server.starttls()

    # SMTP Server Login
    try:
        server.login(mailId, mailPassword)
    except smtplib.SMTPAuthenticationError:
        server.quit()
        print('Username and Password not accepted.')
        return False
    
    # Send message
    try:
        server.sendmail(mailId, toMailId, 'subject: ' + subject + '\n\n' + 'Dear Team' + '\n\n' + message + '\n\n' + 'Best Resgards,' + '\n' + signature)
        return True
    except Exception:
        print('Failed to send email message.')
        return False
    finally:
        server.quit()

def readDaemonEdnAndSendMail():

    # get current time in seconds since epoch
    currentTime     = time.time()

    # initialize from properties file
    config = configparser.RawConfigParser()
    config.read('daemon.properties')
    
    # get filename and time limit threshhold to send message from properties file
    try:
        filename        = config.get('daemon', 'daemon.filename')
        timeLimit       = config.get('daemon', 'daemon.timeLimit')
    except configparser.NoSectionError:
        print('Check daemon.properties file and daemon section the file')
        return False

    # read data from daemonEDN.data file
    try:
        daemonEdnFile   = open(filename)
        daemonEdnData   = daemonEdnFile.read()
    except FileNotFoundError:
        print('File '+ filename + ' is not available for reading.')
        return False

    # define regular expression to find timestamps
    timestampRegEx  = re.compile(r':time (\d+),')
    timestampList   = timestampRegEx.findall(daemonEdnData)

    print('Timestamp list: ' + str(timestampList))

    for t in timestampList:
        difference = currentTime - int(t)
        print(str(difference))

        if (difference > int(timeLimit)):
            print('Timestamp ' + t + ' is older than ' + timeLimit + ' seconds.')
            sendEmail()
            break

readDaemonEdnAndSendMail()
