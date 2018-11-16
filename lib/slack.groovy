def notifyMessage(channelTag, message) {
  def CONSTS = load("constant/main.groovy").getAll()
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}

def errorMessage(channelTag, message) {
  def CONSTS = load("constant/main.groovy").getAll()
  def (channel, credentialsId) = CONSTS.SLACK.CHANNEL_CREDENTIAL_IDS[channelTag]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message, color: "#FF0000"
  }
}
return this