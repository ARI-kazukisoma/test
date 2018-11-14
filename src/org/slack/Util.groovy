package org.slack

// // slackSendを共有ライブラリ内で実行するとエラーとなるのでここでは呼ばないようにする
// // https://github.com/jenkinsci/slack-plugin/issues/348

// void notifyMessage(channel, message) {
//   def lib = library('auto-deploy').org.slack
//   credentialsId = lib.Constants.CHANNEL_CREDENTIAL_IDS[channel]
//   withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
//     slackSend channel: channel, token: token, message: message
//   }
// }

String getToken(credentialsId) {
  script {
    def result = ''
    withCredentials([string(credentialsId: credentialsId, variable: 'token')]) {
      result = token
    }
    return result
  }
}
return this