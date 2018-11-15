
def in_plan(target_env) {
  def CONSTS = load("constant/main.groovy").getAll()
  
  return CONSTS.PLAN_LIST.contains(target_env)
}


return this