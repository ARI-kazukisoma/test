package org.slack
// チャンネル名とJenkinsで管理しているCredentialIDを紐付ける情報
// Slack通知に必要なトークン情報はJenkins側で管理
def call(channel) {
    def settings = [
      '#hooktest': 'tmp_ari_slack_token'
      ]
    echo settings[channel]
}
