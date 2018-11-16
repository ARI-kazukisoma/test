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

  /*
  本来はconstant/main.goovyで管理するべきだが、
  「def CONSTS = load("constant/main.groovy").getAll()」ロードすると
  発生条件不明の「NotSerializableException: java.io.PrintWriter」が発生するのでここで管理する
  */
  if (env.JENKINS_ENV == 'develop') {
    return [
      'CHANNEL_CREDENTIAL_IDS': [
        'admin_channel': ['#hooktest', 'tmp_ari_slack_token'],
        'planner_channel': ['#hooktest', 'tmp_ari_slack_token']
    ]
  } else (env.JENKINS_ENV == 'production') {
    return [
      'CHANNEL_CREDENTIAL_IDS': [
        'admin_channel': ['#hooktest', 'tmp_ari_slack_token'],
        'planner_channel': ['#hooktest', 'tmp_ari_slack_token']
    ]
  }
}


return this