
def is_locked(env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    if (fileExists(env.toLowerCase())) {
      return true
    }
    return false
  }
}

def do_lock(env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    touch("${env.toLowerCase}.lock")
  }
}

def do_unlock(env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    sh "rm ${env.toLowerCase}.lock"
  }
}

return this