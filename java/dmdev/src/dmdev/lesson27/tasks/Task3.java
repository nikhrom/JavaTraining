/*Написать программу, выполняющую поиск по строке всех тегов
* абзацев, в т.ч. тех, у которых есть параметры, например
* <p id="p1">, и замену их на простые теги абзацев <p>*/

package dmdev.lesson27.tasks;

public class Task3 {

    public static void main(String[] args) {
        String regex = "<p .*?>";
        String input = "<div class=\"layout\">\n" +
                "\t\t\t\t\t<div class=\"content_left\">\n" +
                "\t\t\t\t\t\t<div class=\"vote plus\">\n" +
                "\t\t\t\t\t\t\t<span itemprop=\"upvoteCount\">5</span><i></i>\n" +
                "\t\t\t\t\t\t</div>\t\t\t\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"content language-none\">\n" +
                "\t\t\t\t\t\t<div class=\"text language-markup\" itemprop=\"text\">\n" +
                "\t\t\t\t\t\t\t<p>У меня есть приложение UI в Qt, у меня есть несколько функций, которые запускают SQL-запросы с большим масштабом, которые возвращают тысячи результатов.</p>\n" +
                "<p id=\"p1\"> когда кнопка, которая запускает этот запрос, щелкнула окна пользовательского интерфейса, просто мгновенно переходит к \"не отвечает\", однако я вижу на консольных выводах, что все по-прежнему работает в фоновом режиме. Как только функция заканчивается, данные отображаются как ожидалось, и пользовательский интерфейс снова реагирует и полностью функционирует.</p>\n" +
                "<p >Я знаю, что это связано с тем, что функция зацикливается тысячами раз из-за большого количества результатов, но я надеялся, что могу просто положить в панель загрузки, которая прогрессирует по мере того, как поиск делает, а не просто блокируя окно, заставляя его выглядеть так, как программа разбилась. AFAIK У меня нет лука-порея, так есть ли у кого-нибудь какие-либо предложения?</p>\n" +
                "<p>oh также im думает, что это не недостаток памяти, потому что, когда я нажимаю эту кнопку, диспетчер задач показывает только пару МБ памяти, которые используются для этого процесса, и процессор никоим образом не максимизирует либо</p>\t\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"info collapsed\">\n" +
                "\t\t\t\t\t\t\t<div class=\"info_right\">\t\n" +
                "\t\t\t\t\t\t\t\t<span itemprop=\"author\" itemscope itemtype=\"http://schema.org/Person\"><span itemprop=\"additionalName\">AngryDuck</span></span>\n" +
                "\t\t\t\t\t\t\t\t<time itemprop=\"dateCreated\" datetime=\"2013-04-22T16-34-58.000Z\">22 апр. 2013, в 16:34</time>\n" +
                "\t\t\t\t\t\t\t</div>\t\n" +
                "\t\t\t\t\t\t\t<div class=\"info_inlink\">Поделиться</div>\n" +
                "\t\t\t\t\t\t\t<a class=\"info_outlink\" href=\"https://stackoverflow.com/questions/16149643/qt-program-hangs-not-responding-until-function-ends-then-starts-working-again\" target=\"_blank\" rel=\"noopener\">Источник</a>\n" +
                "\t\t\t\t\t\t\t<div class=\"info_inlink_url\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" name=\"url\" value=\"https://overcoder.net/q/929991/программа-qt-зависает-не-отвечает-пока-функция-не-завершится-затем-снова-начнет\">\n" +
                "\t\t\t\t\t\t\t\t<button></button>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\t";


        String result = input.replaceAll(regex, "<p>");

        System.out.println(result);

    }

}
