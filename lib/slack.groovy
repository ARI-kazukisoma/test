def notifyMessage(channelTag, message, CONSTS = null) {
  if (CONSTS == null) {
    CONSTS = load("constant/main.groovy").getAll()
  }
  def (channel, credentialsId) = CONSTS.CHANNEL_CREDENTIAL_IDS[channelTag]

  // 発生条件が不明なNotSerializableExceptionが発生することがあるのでtry catch()でもみ消す。
  // 送信自体はできている。
  try {
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      slackSend channel: channel, token: token, message: message
    }
  } catch(Exception e) {}
}

def errorMessage(channelTag, message) {
  if (CONSTS == null) {
    CONSTS = load("constant/main.groovy").getAll()
  }
  def (channel, credentialsId) = CONSTS.CHANNEL_CREDENTIAL_IDS[channelTag]

  // 発生条件が不明なNotSerializableExceptionが発生することがあるのでtry catch()でもみ消す。
  // 送信自体はできている。
  try {
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      slackSend channel: channel, token: token, message: message, color: "#FF0000"
    }
  }
}

return this