def notifyMessage(channelTag, message) {
  def (channel, credentialsId) = getChannelCredential(channelTag)
  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
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