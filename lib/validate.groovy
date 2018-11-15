
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
    masterTag -> masterTags.push(_checkMasterTagFormat(masterTag))
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
    def tagName = masterTag[2]
    def unixtime = masterTag[3]

    if (success == false) {
      // １つでもフォーマットにミスがあればエラー
      return [false, null]
    }

    if (unixtime == null) {
      // １つでも日付の指定がなければエラー
      return [false, null]
    }

    resultMasterTags.push("${tagName}:${unixtime}")
  }

   return [true, resultMasterTags.join(" ")]
}

def _checkMasterTagFormat(masterTag) {

  def libDatetime = load("lib/datetime.groovy")
  def (success, tagName, strDatetime) = _splitMasterTag(masterTag)

  if (success == false) {
    return [false, null, null, null,]
  }

  if (strDatetime == null) {
    return [true, masterTag, tagName, null]
  }

  unixtime = libDatetime.stringToUnixtime(strDatetime)

  if (unixtime == null) {
    return [false, null, null, null, null]
  }

  return [true, masterTag, tagName, unixtime]
}

def _splitMasterTag(masterTag) {
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