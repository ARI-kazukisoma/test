
def isLocked(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    if (fileExists("${target_env.toLowerCase()}.lock")) {
      return true
    }
    return false
  }
}

def doLock(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    sh " touch ${target_env.toLowerCase()}.lock"
  }
}

def doUnlock(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    sh "rm ${target_env.toLowerCase()}.lock"
  }
}

def isMutiLimitOrver() {
  def CONSTS = load("constant/main.groovy").getAll()
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    def existLock = sh returnStdout: true, script: "ls "
    existLocks = existEnv.split("\n").toList()

    if (existLocks.size() >= CONSTS.MAX_MULTI_EXEC_NUM) {
      return true
    }
    return false
  }
}

return this