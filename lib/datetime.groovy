/*
時間を扱う関数群
TIMEZONEの設定は定数で定義されています。
*/

import java.text.SimpleDateFormat

Long string_to_unixtime(dateString, format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("constant/main.groovy").get_all()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.parse(dateString).time / 1000
}

Date now(format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("constant/main.groovy").get_all()
  def date = new Date()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.format(date)

}
Date string_to_datetime(dateString, format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("constant/main.groovy").get_all()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.parse(dateString).format(format)
}

return this