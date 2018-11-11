/*
定数を定義
大きな機能ごとにパブリックなclassを定義し、
classに変更不可の静的な変数を定義する。

使用例)

constants/main.groovy
-----------------------------
public class SAMPLE {
  public static final NUM = 100
}

Jenkinsfile
-----------------------------

pipeline {
  agent any
  stages {
    stage("インポート定数") {
      steps {
        script {
          def CONST = load("${pwd()}/constants/main.groovy")
          echo CONST.SAMPLE.NUM
        }
      }
    }
  }
}

*/

// Slackに関する定数の定義
public class SLACK {
  // Slackのチャンネルと認証IDの紐づけ情報
  public static final CHANNEL_CREDENTIAL_IDS = [
    "#hooktest": "approval_slack_token",
  ]
}

Closure TEST_CLASS = {
  public class TEST_CLASS {
    public static NUM = 10
  }
}

def num = 10

return this