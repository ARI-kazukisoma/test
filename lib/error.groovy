
def notifyError(channel, errorCode, templateFile, binding=[],isException=false) {
  def slack = load("lib/slack.groovy")
  def template = load("lib/template.groovy")
  def ERROR = load("constant/main.groovy").getErrorCodeAll()

  binding.put('__error_code', errorCode)
  binding.put('__error_message', ERROR[errorCode])
  def message = template.toString(templateFile, binding)

  slack.notifyMessage(channel, message)

  if (isException) {
    customError(errorCode, errorMessage)
  }
}

def customError(errorCode, message=null) {

  if (message) {
    error("${errorCode}：${message}")
  }
  
  def ERROR = load("constant/main.groovy").getErrorCodeAll()
  error("${errorCode}：${ERROR[errorCode]}")
  
}
return this