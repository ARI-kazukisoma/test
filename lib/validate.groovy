
def in_plan(target_env) {
  def CONSTS = load("constant/main.groogy").getAll()
  
  CONSTS.contains(target_env)
}


return this