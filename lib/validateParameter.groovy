/**
パラメータごとのバリデーションチェックする関数群
*/

/**
マスタータグのチェック
併せてタグ変換後の値も返す
*/
def checkMasterTags(text) {

  def validate = load("lib/validate.groovy")

  if (validate.isNull(text)) {
    return [false, null, 'VLD001']
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
      return [false, null, 'VLD002']
    } 

    if (unixtime) {
      return [false, null, 'VLD002']
    }

    return [true, tagName, null]

  }

  // タグ指定が複数の場合
  def resultMasterTags = []
  for (masterTag in masterTags) {
    def success = masterTag[0]
    def transTagName = masterTag[2]
    def unixtime = masterTag[3]

    if (success == false) {
      // １つでもフォーマットにミスがあればエラー
      return [false, null, 'VLD002']
    }

    if (unixtime == null) {
      // unixtimeがなければ現在の時間に合わせる。
      def libDatetime = load("lib/datetime.groovy")
      def now = libDatetime.now("yyyy/MM/dd HH:mm")
      unixtime = libDatetime.stringToUnixtime(now, "yyyy/MM/dd HH:mm")
    }

    resultMasterTags.push("${transTagName}:${unixtime}")
  }

   return [true, resultMasterTags.join(" "), null]
}

/**
ENVのパラメータチェック
払い出されていない環境ならエラー
*/
def checkExistsEnv(targetEnv) {

  def validate = load("lib/validate.groovy")

  if (validate.isNull(targetEnv)) {
    return [false,  "VLD001"]
  }

  if (validate.isEnv(targetEnv) == false) {
    return [false, "VLD006"]
  }

  if (validate.existsEnv(targetEnv) == false) {
    return [false, 'VLD005']
  }

  return [true, null]
}

/**
ENVのパラメータチェック
払い出されている環境ならエラー
*/
def checkNotExistsEnv(targetEnv) {
  def validate = load("lib/validate.groovy")

  if (validate.isNull(targetEnv)) {
    return [false, 'VLD001']
  }

  if (validate.isEnv(targetEnv) == false) {
    return [false, 'VLD006']
  }

  if (validate.existsEnv(targetEnv)) {
    return [false, 'VLD004']
  }

  return [true, null]
}

def checkBranch(branch) {
  def validate = load("lib/validate.groovy")

  if (validate.isNull(branch)) {
    return [false, 'VLD001']
  }

  def (success, minorVersion) = validate.checkBranchName(branch)

  if (success == false) {
    return [false, 'VLD002', null]
  }
  return [true, null, minorVersion]
}

def checkReason(reason) {
  def validate = load("lib/validate.groovy")

  if (validate.isNull(reason)) {
    return [false, 'VLD001']
  }
  return [true, null]

}

return this