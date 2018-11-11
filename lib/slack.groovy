import groovy.transform.Field

@Field
def _channelCredentialIds = [
  "#hooktest": "approval_slack_token",
]

def notify_message(channel, message) {
  def credentialsId = _channelCredentialIds[channel]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}

return this