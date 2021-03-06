String toString(fileName, binding) {
  def template_dir = "${JENKINS_HOME}/workspace/${JOB_NAME}/template"
  def f = new File("${template_dir}/${fileName}")
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  message = template.toString()
  return message
}

String getValidateError(errorCode, paramName) {
  def ERROR = load("constant/error.groovy").getAll()
  def binding = [
    "param_name": paramName,
    "error_message": ERROR[errorCode]
  ]
  return toString("error/validate.template", binding)
}

String getValidateAllError(errorMessages) {

  buildUser = 'API'
  wrap([$class: 'BuildUser']) {
    try {
      // API経由だとエラーになる
      buildUser = BUILD_USER
    } catch(Exception e) {}
  }
  def ERROR = load("constant/error.groovy").getAll()
  def binding = [
    "user_name": buildUser,
    "job_name": JOB_NAME,
    "job_number": BUILD_NUMBER, 
    "job_url": "${env.JENKINS_URL}job/${JOB_NAME}/${BUILD_NUMBER}/",
    "error_messages": errorMessages
  ]
  return this.toString("error/validate_all.template", binding)
}
String getSafetyError(errorCode) {

  buildUser = 'API'
  wrap([$class: 'BuildUser']) {
    try {
      // API経由だとエラーになる
      buildUser = BUILD_USER
    } catch(Exception e) {}
  }

  def ERROR = load("constant/error.groovy").getAll()
  def binding = [
    "user_name": buildUser,
    "job_number": BUILD_NUMBER, 
    "job_name": JOB_NAME,
    "job_url": "${env.JENKINS_URL}job/${JOB_NAME}/${BUILD_NUMBER}/",
    "error_message": ERROR[errorCode]
  ]
  return toString("error/safety.template", binding)
}

return this