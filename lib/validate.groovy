
def isPlan(targetEnv) {
  def CONSTS = load("constant/main.groovy").getAll()
  
  return CONSTS.PLAN_LIST.contains(targetEnv)
}

/**
作成されている環境ならtrue
*/
def existsPlan(targetEnv) {
  dir(env.ENV_CONFIGURATION_PATH) {
    def existPlan = sh returnStdout: true, script: 'ls'
    def existPlans = existPlan.split("\n").toList()
    
    envs = [targetEnv] - existPlans 

    if (envs.size() == 0) {
      return true
    }

    return false
  }
}

def isNull(val, nullVals=[null, '']) {
  if (nullVals.contains(val)) {
    return true
  }

  return nullVals.contains(val.trim())
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

  unixtime = libDatetime.stringToUnixtime(strDatetime, "yyyy/MM/dd HH:mm")

  if (unixtime == null) {
    // unixtime変換に失敗
    return [false, null, null, null]
  }

  if (unixtime < 0) {
    // 1970/01/01 9時0分0秒よりも古いとマイナスになるので0とする
    unixtime = 0
  }

  return [true, masterTag, transTagName, unixtime]
}

def checkTagNameFormat(tagName) {
  if (tagName ==~ /te:.*/) {
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