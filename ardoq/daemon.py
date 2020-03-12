import configparser
import smtplib
import sys

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

# set daemon parameters
filename        = config.get('daemon', 'daemon.filename')
timestampLimit  = config.get('daemon', 'daemon.timestamplimit')

def findOldTimestamp(filename):
    engine.say(audio)
    engine.runAndWait()


print(domain + ' ' + port + ' ' + mailId + ' ' + toMailId + ' ' + mailPassword + ' ' + subject + ' ' + message + ' ' + signature + ' ' + filename + ' ' + timestampLimit)

'''
fadd = ''                                       # sender's email address
tadd = ''                                       # receiver's email address
msg = 'Mail sent through Python!'               # Message to be sent!
username = ''                                   # Your username(email ID)
password = ''                                   # Your password for above email ID
server = smtplib.SMTP('smtp.gmail.com',587)
server.ehlo()
server.starttls()
server.login(username,password)
server.sendmail(fadd,tadd,msg)

'''
