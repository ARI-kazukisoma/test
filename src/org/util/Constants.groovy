package org.util

class Constants {

    public static final PLAN_CONF_FILE_NAME = [
      'PROVISIONING': 'provisioning.json',
      'COMMON': 'common.json',
      'DEPLOY_APP': 'deploy_app.json',
      'UPDATE_DATA': 'update_data.json'
    ]
    public static final API_URL = [
      'APPROVAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し/buildWithParameters?token=zpUcDnr5xcgppFr",
      'REFUSAL_CREATE_PLAN_JOB': "${env.JENKINS_URL}job/開発環境新規払い出し拒否/buildWithParameters?token=n3T5kMGAVX6NCFE"
    ]
}