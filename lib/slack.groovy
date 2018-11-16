void notifyMessage(channelTag, message) {
  echo "aaaaaaaaaaaaaaaa"
  echo channelTag
  println CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]
  CONSTS = load("constant/main.groovy").getAll()
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]
  echo "bbbbbbbb"

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this