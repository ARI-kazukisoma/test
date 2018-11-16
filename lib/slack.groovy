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

  /*
  本来はconstant/main.goovyで管理するべきだが、
  「def CONSTS = load("constant/main.groovy").getAll()」ロードすると
  発生条件不明の「NotSerializableException: java.io.PrintWriter」が発生するのでここで管理する
  */

  settings = []
  if (env.JENKINS_ENV == 'develop') {
    settings = [
      'admin_channel': ['#hooktest', 'tmp_ari_slack_token'],
      'planner_channel': ['#hooktest', 'tmp_ari_slack_token']
    ]
  } else (env.JENKINS_ENV == 'production') {
    settings = [
      'admin_channel': ['#hooktest', 'tmp_ari_slack_token'],
      'planner_channel': ['#hooktest', 'tmp_ari_slack_token']
    ]
  }

  return settings[channelTag]
}


return this