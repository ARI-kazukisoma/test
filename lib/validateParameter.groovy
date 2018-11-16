/**
パラメータごとのバリデーションチェックする関数群
*/

/**
マスタータグのチェック
併せてタグ変換後の値も返す
*/
def checkMasterTags(text) {

  def validate = load("lib/validate.groovy")
  def error = load("lib/error.groovy")
  def userName  = ''

  wrap([$class: 'BuildUser']) {
    userName = BUILD_USER
  }

  if (validate.isNull(text)) {
    error.notifyError(
      'admin_channel', 'VLD001', 'error/validate.template', ['user_name': userName], true)
  }

  masterTags = []
  text.trim().split("\n").toList().collect{
    // １行ずつマスタータグのフォーマットチェックを行い、分解した情報を格納
    masterTag -> masterTags.push(validate.checkMasterTagFormat(masterTag))
  }

  // タグ指定が１つの場合
  if (masterTags.size() == 1) {
    def (success, masterTag, tagName, unixtime) = masterTags[0]

    if (success == false) {
      return [false, null]
    } 

    if (unixtime) {
      return [false, null]
    }

    return [true, tagName]

  }

  // タグ指定が複数の場合
  def resultMasterTags = []
  for (masterTag in masterTags) {
    def success = masterTag[0]
    def transTagName = masterTag[2]
    def unixtime = masterTag[3]

    if (success == false) {
      // １つでもフォーマットにミスがあればエラー
      return [false, null]
    }

    if (unixtime == null) {
      // unixtimeがなければ現在の時間に合わせる。
      libDatetime = load("lib/datetime.groovy")
      def now = libDatetime.now("yyyy/MM/dd HH:mm")
      unixtime = libDatetime.stringToUnixtime(now, "yyyy/MM/dd HH:mm")
    }

    resultMasterTags.push("${transTagName}:${unixtime}")
  }

   return [true, resultMasterTags.join(" ")]
}

/**
プラン環境のENVのパラメータチェック
*/
def checkPlanEnv(targetEnv) {

  def validate = load("lib/validate.groovy")

  if (validate.isNull(targetEnv)) {
    return false
  }

  if (validate.isPlan(targetEnv)) {
    return true
  }

  return false
}

def checkBranch(branch) {
  def validate = load("lib/validate.groovy")

  if (validate.isNull(branch)) {
    return false
  }
  return true

}

def checkReason(reason) {
  def validate = load("lib/validate.groovy")

  if (validate.isNull(reason)) {
    return false
  }
  return true

}

return this