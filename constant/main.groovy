/*
定数を定義

使用例)

Jenkinsfile
-----------------------------

pipeline {
  agent any
  stages {
    stage("定数を取得") {
      steps {
        script {
          CONSTS = load("constants/main.groovy").get_all()
          echo CONST.SAMPLE.NUM
        }
      }
    }
  }
}
*/

def get_all() {
  return [
    'SLACK': [
      'CHANNEL_CREDENTIAL_IDS': [
        '#hooktest': 'tmp_ari_slack_token'
      ]
    ]
  ]
}

return this