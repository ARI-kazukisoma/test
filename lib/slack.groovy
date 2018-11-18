def notifyMessage(channelTag, message, CONSTS = null) {
  if (CONSTS == null) {
    CONSTS = load("constant/main.groovy").getAll()
  }
  def (channel, credentialsId) = CONSTS.CHANNEL_CREDENTIAL_IDS[channelTag]

  // 発生条件が不明な不明のNotSerializableExceptionが発生することがあるのでtry catch()でもみ消す。
  // 送信自体はできている。
  try {
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      slackSend channel: channel, token: token, message: message
    }
  } catch(Exception e) {}
}

def errorMessage(channelTag, message) {
  def (channel, credentialsId) = getChannelCredential(channelTag)
  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message, color: "#FF0000"
  }
}

def getChannelCredential(channelTag) {
  def CONSTS = load("constant/main.groovy").getAll()
  return CONSTS.CHANNEL_CREDENTIAL_IDS[channelTag]
}


return this