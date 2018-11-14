
def is_locked(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    if (fileExists(target_env.toLowerCase())) {
      echo "aaaaaaa"
      return true
    }
    return false
  }
}

def do_lock(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    sh " touch ${target_env.toLowerCase()}.lock"
  }
}

def do_unlock(target_env) {
  dir(env.EXECUTE_LOCK_FILE_PATH) {
    sh "rm ${target_env.toLowerCase()}.lock"
  }
}

return this