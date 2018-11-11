def notify_message(channel, message) {
  def CONST = load("${pwd()}/constants/main.groovy")
  def slack CONST.SLACK()
  def credentialsId = CONST.SLACK.CHANNEL_CREDENTIAL_IDS[channel]

  // withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
  //   slackSend channel: channel, token: token, message: message
  // }
}
return this