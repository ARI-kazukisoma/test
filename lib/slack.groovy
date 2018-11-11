import groovy.transform.Field

@Field
def _channelCredentialIds = [
  "#hooktest": "approval_slack_token",
]

def notify_message(channel, message) {
  def credentialsId = _channelCredentialIds[channel]

  def t
  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    t = token
    slackSend channel: channel, token: token, message: message
  }
  echo t
}

return this