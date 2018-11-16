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
          def CONSTS = load("../${JOB_NAME}/constant/main.groovy").get_all()
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
        'admin_channel': ['#hooktest', 'tmp_ari_slack_token']
      ]
    ],
    'API_URL': [
      'APPROVAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し/buildWithParameters?token=zpUcDnr5xcgppFr",
      'REFUSAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し拒否/buildWithParameters?token=n3T5kMGAVX6NCFE",
      'APPROVAL_DELETE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境削除/buildWithParameters?token=ajUMKN7DW98EwZF",
      'REFUSAL_DELETE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境削除拒否/buildWithParameters?token=bUNpYTmrEDiujRD"
    ],
    'TIMEZONE': 'JST',
    'PLAN_CONF_FILE_NAME': [
      'PROVISIONING': 'provisioning.json',
      'COMMON': 'common.json',
      'DEPLOY_APP': 'deploy_app.json',
      'UPDATE_DATA': 'update_data.json'
    ],
    'PLAN_LIST': [
      'Plan07',
      'Plan08',
      'Plan09',
      'Plan10',
      'Plan11',
      'Plan12',
      'Plan13',
      'Plan14',
      'Plan15',
      'Plan16',
      'Plan17',
      'Plan18',
      'Plan19',
      'Plan20',
    ],
    'MASTER_TAG': [
      'DELIMITER': '&'
    ]
  ]
}

def getErrorCodeAll() {
  return [
    'VLD001': "入力は必須です。" ,
    'VLD002': "入力フォーマットが間違っています。",
    'VLD003': "不正な値です。"
  ]
}

return this