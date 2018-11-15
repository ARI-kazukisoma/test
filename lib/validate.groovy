
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

def checkMasterTagFormat(masterTag) {

  def libDatetime = load("lib/datetime.groovy")
  def (success, tagName, strDatetime) = splitMasterTag(masterTag)

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