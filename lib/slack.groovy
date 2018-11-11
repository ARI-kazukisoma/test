def notify_message(channel, message) {
  load("${pwd()}/constants/main.groovy")
  // def credentialsId = CONST.SLACK.CHANNEL_CREDENTIAL_IDS[channel]
  echo num

  // withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
  //   slackSend channel: channel, token: token, message: message
  // }
}
return this