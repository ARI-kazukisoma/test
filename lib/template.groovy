String toString(fileName, binding) {
  sh "touch test.txt"
  def ff = new File("text.txt")
  sh "sleep 10"
  def template_dir = "./template"
  def f = new File("${template_dir}/${fileName}")
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

return this