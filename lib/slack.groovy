def notifyMessage(channelTag, message, CONSTS = null) {
  def (channel, credentialsId) = getChannelCredential(channelTag, CONSTS)

  // 発生条件が不明のNotSerializableExceptionが発生することがあるのでtry catch()でもみ消す。
  // 送信自体はできている。
  try {
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      slackSend baseUrl: CONSTS.SLACK_BASE_URL, channel: channel, token: token, message: message
    }
  } catch(java.io.NotSerializableException e) {}
}

def errorMessage(channelTag, message, CONSTS = null) {
  def (channel, credentialsId) = getChannelCredential(channelTag, CONSTS)
  // 発生条件が不明のNotSerializableExceptionが発生することがあるのでtry catch()でもみ消す。
  // 送信自体はできている。
  try {
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      slackSend baseUrl: CONSTS.SLACK_BASE_URL, channel: channel, token: token, message: message, color: "#FF0000"
    }
  } catch(java.io.NotSerializableException e) {}
}

def getChannelCredential(channelTag, CONSTS = null) {
  if (CONSTS == null) {
    // ここのloadで発生条件が不明なNotSerializableExceptionが発生することがあるのでその際は外からCONSTSを渡している。
    CONSTS = load("constant/main.groovy").getAll()
  }
  return CONSTS.CHANNEL_CREDENTIAL_IDS[channelTag]
}


return this