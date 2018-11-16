void notifyMessage(channelTag, message) {
  CONSTS = load("../${JOB_NAME}/constant/main.groovy").getAll()
  echo channelTag
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]
  echo "bbbbbbbb"

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this