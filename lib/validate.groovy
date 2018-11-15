
def inPlan(targetEnv) {
  def CONSTS = load("constant/main.groovy").getAll()
  
  return CONSTS.PLAN_LIST.contains(targetEnv)
}

def isNull(val, nullVals=[null, '', ' ']) {
  return nullValus.contains(val.trim())
}

def checkMasterTagFormat(masterTag) {

  def libDatetime = load("lib/datetime.groovy")
  def (success, tagName, strDatetime) = splitMasterTag(masterTag)

  if (success) {
    return [false, null, null, null,]
  }

  unixtime = libDatetime.stringToUnixtime(strDatetime)
  if (unixtime) {
    return [false, null, null, null]
  }

  return [true, masterTag, tagName, unixtime]
}

def splitMasterTag(masterTag) {
  def CONSTS = load("constant/main.groovy").getAll()

  echo "aaaa ${masterTag}"
  splitVals = [].push(masterTag.split(CONSTS.MASTER_TAG.DELIIMITER))
  splitVals = splitVals.flatten()

  if (splitVals.length == 2) {
    return [true, splitVals[0], splitVals[1]]
  } else if (splitVals.length == 1) {
    return [true, splitVals[0]]
  }

  return [false, null, null]
}

return this