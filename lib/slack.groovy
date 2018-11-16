def notifyMessage(channelTag, message, CONSTS = null) {

  if (CONSTS == null) {
    echo "aaaa"
    try {
      CONSTS = load("constant/main.groovy").getAll()
    } catch(Exception e) {}
  }
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this