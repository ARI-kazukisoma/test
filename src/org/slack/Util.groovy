package org.slack

void notifyMessage(channel, message) {
  // CONSTS = load("constant/main.groovy").getAll()
  // credentialsId = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channel]
  credentialId = org.slack.channelCredentialIds channel

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}

void echoTest(str) {
  echo str
}
return this