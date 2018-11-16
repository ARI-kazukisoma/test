void notifyMessage(channelTag, message, CONSTS=null) {
  echo "aaaaaaaaaaaaaaaa"
  echo channelTag
  // 呼び出す階層が深いとNotSerializableExceptioneエラーが発生するので回避策
  // 原因はよくわかっていない
  if (CONSTS == null) {
    def CONSTS = load("constant/main.groovy").getAll()
  }
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}
return this