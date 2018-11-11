import groovy.transform.Field

@Field
def _channelCredentialIds = [
  "#hooktest": "test_ari_git",
]

def notify_message(channel, message) {
  def credentialsId = _channelCredentialIds["#hooktest"]

  withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
    slackSend channel: channel, token: token, message: message
  }
}

return this