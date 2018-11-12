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
          def CONSTS = load("constant/main.groovy").get_all()
          echo CONSTS.SAMPLE.NUM
        }
      }
    }
  }
}
*/

def getAll() {
  return [
    'SLACK': [
      'CHANNEL_CREDENTIAL_IDS': [
        '#hooktest': 'tmp_ari_slack_token'
      ]
    ],
    'API_URL': [
      'APPROVAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し/buildWithParameters?token=zpUcDnr5xcgppFr",
      'REFUSAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し拒否/buildWithParameters?token="
    ],
    'TIMEZONE': 'JST'
  ]
}

return this