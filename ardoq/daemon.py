import configparser
import smtplib
import sys

def sendEmail():

    # initialize from properties file
    config = configparser.RawConfigParser()
    config.read('daemon.properties')

    # set mail parameters
    domain          = config.get('email', 'email.domain')
    port            = config.get('email', 'email.port')
    mailId          = config.get('email', 'email.mailid')
    mailPassword    = sys.argv[1]
    toMailId        = config.get('email', 'email.tomailid')
    subject         = config.get('email', 'email.subject')
    message         = config.get('email', 'email.message')
    signature       = config.get('email', 'email.signature')

    # send mail
    server = smtplib.SMTP(domain,int(port))
    server.ehlo()
    server.starttls()
    server.login(mailId, mailPassword)
    server.sendmail(mailId, toMailId, 'subject: ' + subject + '\n\n' + 'Dear Team' + '\n\n' + message + '\n\n' + 'Best Resgards,' + '\n' + signature)

def getOldestTimestamp():

    # initialize from properties file
    config = configparser.RawConfigParser()
    config.read('daemon.properties')
    
    # set daemon parameters
    filename        = config.get('daemon', 'daemon.filename')
    timestampLimit  = config.get('daemon', 'daemon.timestamplimit')

    print(filename + ' ' + timestampLimit)

# sendEmail()
getOldestTimestamp()
