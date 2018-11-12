def notify_message(channel, message) {
  CONSTS = load("constant/main.groovy").get_all()
  credentialsId = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channel]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this