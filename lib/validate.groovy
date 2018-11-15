
def inPlan(targetEnv) {
  def CONSTS = load("constant/main.groovy").getAll()
  
  return CONSTS.PLAN_LIST.contains(targetEnv)
}

def isNull(val, nullVals=[null, '']) {
  if (nullVals.contains(val)) {
    return true
  }

  return nullVals.contains(val.trim())
}


def checkMasterTags(text) {

  if (isNull(text)) {
    return [false, null]
  }

  masterTags = []
  text.trim().split("\n").toList().collect{
    // １行ずつマスタータグのフォーマットチェックを行い、分解した情報を格納
    masterTag -> masterTags.push(checkMasterTagFormat(masterTag))
  }

  // タグ指定が１つの場合
  if (masterTags.size() == 1) {
    def (success, masterTag, tagName, unixtime) = masterTags[0]

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
      // １つでも日付の指定がなければエラー
      return [false, null]
    }

    resultMasterTags.push("${transTagName}:${unixtime}")
  }

   return [true, resultMasterTags.join(" ")]
}

/**
マスタータグ(単体)のフォーマットチェック
問題なければtrueと変換後の値が返される

*/
def checkMasterTagFormat(masterTag) {

  def libDatetime = load("lib/datetime.groovy")
  def (success, tagName, strDatetime) = splitMasterTag(masterTag)

  if (success == false) {
    // タグ名と日付の分割に失敗
    return [false, null, null, null]
  }

  def (tagNameSuccess, transTagName) = checkTagNameFormat(tagName)

  if (tagNameSuccess == false) {
    // タグ名の命名がおかしい
    return [false, null, null, null]
  }

  if (strDatetime == null) {
    // 日付の指定がなければ以降の処理はしない
    return [true, masterTag, transTagName, null]
  }

  unixtime = libDatetime.stringToUnixtime(strDatetime)

  if (unixtime == null) {
    // unixtime変換に失敗
    return [false, null, null, null]
  }

  return [true, masterTag, transTagName, unixtime]
}

def checkTagNameFormat(tagName) {
  if (tagName ==~ /te:.*/) {
    echo tagName
    // 「te:～」 を「te_～」に変換
    return [true, tagName.replaceFirst(/te:/, 'te_')]
  }
  return [false, null]
}

def splitMasterTag(masterTag) {
  def CONSTS = load("constant/main.groovy").getAll()
  def splitVals = []
  for (splitVal in masterTag.split(CONSTS.MASTER_TAG.DELIMITER)) {
    splitVals.push(splitVal)
  }

  if (splitVals.size() == 2) {
    return [true, splitVals[0], splitVals[1]]
  } else if (splitVals.size() == 1) {
    return [true, splitVals[0]]
  }

  return [false, null, null]
}

return this