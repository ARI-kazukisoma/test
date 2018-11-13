package org.slack

void notifyMessage(channel, credentialsId, message) {
  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}

void echoTest(str) {
  echo str
}
return this