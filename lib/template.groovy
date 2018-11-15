String toString(fileName, binding) {
  def template_dir = "${JENKINS_HOME}/workspace/${JOB_NAME}/template"
  def f = new File("${template_dir}/${fileName}")
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

return this