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
          def CONSTS = load("constant/main.groovy").getAll()
          echo CONSTS.SAMPLE.NUM
        }
      }
    }
  }
}
*/

def getAll() {

  def environment = []
  if (env.JENKINS_ENV == 'develop') {
    environment = getDevelop()
  } else if (env.JENKINS_ENV == 'product') {
    environment = getProduct()
  }

  base = [
    'TIMEZONE': 'JST',
    'ENV_CONF_FILE_NAME': [
      'PROVISIONING': 'provisioning.json',
      'COMMON': 'common.json',
      'DEPLOY_APP': 'deploy_app.json',
      'UPDATE_DATA': 'update_data.json'
    ],
    'ENV_LIST': [
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
    ],
    'MAX_MULTI_EXEC_NUM': 10,
  ]

  def all = base + environment
  return all

}

// 開発環境用の設定(差分)
def getDevelop() {
  return [
    'CHANNEL_CREDENTIAL_IDS': [
      'admin_channel': ['#hooktest', 'tmp_ari_slack_token'],
      'planner_channel': ['#hooktest', 'tmp_ari_slack_token']
    ],
    'SLACK_BASE_URL': 'https://delightworks.slack.com/services/hooks/jenkins-ci/',
    'API_URL': [
      'APPROVAL_CREATE_ENV_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し/buildWithParameters?token=zpUcDnr5xcgppFr",
      'REFUSAL_CREATE_ENV_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し拒否/buildWithParameters?token=n3T5kMGAVX6NCFE",
      'APPROVAL_DELETE_ENV_JOB': "${env.JENKINS_URL}job/開発環境削除/buildWithParameters?token=ajUMKN7DW98EwZF",
      'REFUSAL_DELETE_ENV_JOB': "${env.JENKINS_URL}job/開発環境削除拒否/buildWithParameters?token=bUNpYTmrEDiujRD"
    ],

  ]

}

// 本番環境用の設定(差分)
def getProduct() {
  return [
    'CHANNEL_CREDENTIAL_IDS': [
      'admin_channel': ['avalon_jenkinsデプロイ通知', 'slack_token_for_admin_channel'],
      'planner_channel': ['#hooktest', 'tmp_ari_slack_token'],
    ],
    'SLACK_BASE_URL': 'https://kazukisomaari.slack.com/services/hooks/jenkins-ci/',
    'API_URL': [
      'APPROVAL_CREATE_ENV_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し/buildWithParameters?token=zpUcDnr5xcgppFr",
      'REFUSAL_CREATE_ENV_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し拒否/buildWithParameters?token=n3T5kMGAVX6NCFE",
      'APPROVAL_DELETE_ENV_JOB': "${env.JENKINS_URL}job/開発環境削除/buildWithParameters?token=ajUMKN7DW98EwZF",
      'REFUSAL_DELETE_ENV_JOB': "${env.JENKINS_URL}job/開発環境削除拒否/buildWithParameters?token=bUNpYTmrEDiujRD"
    ],
  ]
}
return this
