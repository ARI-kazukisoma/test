void notifyMessage(channelTag, message) {
  // 呼び出す階層が深いとNotSerializableExceptioneエラーが発生するので回避策
  // 原因はよくわかっていない
  def CONSTS = []
  try {
    CONSTS = load("constant/main.groovy").getAll()
  } catch (java.io.NotSerializableException e) {}
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this