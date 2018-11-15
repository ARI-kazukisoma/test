
def in_plan(target_env) {
  def CONSTS = load("constant/main.groovy").getAll()
  
  CONSTS.contains(target_env)
}


return this