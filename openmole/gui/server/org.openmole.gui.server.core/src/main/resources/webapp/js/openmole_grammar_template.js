define('ace/mode/openmole',function(require, exports, module) {

var oop = require("ace/lib/oop");

var ScalaMode = require("ace/mode/scala").Mode;
var OpenMOLEHighlightRules = require("ace/mode/openmole_highlight_rules").OpenMOLEHighlightRules;
var MatchingBraceOutdent = require("./matching_brace_outdent").MatchingBraceOutdent;


var Mode = function() {
    this.HighlightRules = OpenMOLEHighlightRules;
    this.$outdent = new MatchingBraceOutdent();

};
oop.inherits(Mode, ScalaMode);

exports.Mode = Mode;
});



define('ace/mode/openmole_highlight_rules', function(require, exports, module) {

var oop = require("ace/lib/oop");
var ScalaHighlightRules = require("ace/mode/scala_highlight_rules").ScalaHighlightRules;


var OpenMOLEHighlightRules = function() {


    var keywords = (
            "case|default|do|else|for|if|match|while|throw|return|try|trye|catch|finally|yield|" +
            "abstract|class|def|extends|final|forSome|implicit|implicits|import|lazy|new|object|null|" +
            "override|package|private|protected|sealed|super|this|trait|type|val|var|with|" +
            "assert|assume|require|print|println|printf|readLine|readBoolean|readByte|readShort|" + // package scala
            "readChar|readInt|readLong|readFloat|readDouble|"+
            ##OMKeywords##
    );

    var buildinConstants = ("true|false");

    var langClasses = (
        "AbstractMethodError|AssertionError|ClassCircularityError|"+
        "ClassFormatError|Deprecated|EnumConstantNotPresentException|"+
        "ExceptionInInitializerError|IllegalAccessError|"+
        "IllegalThreadStateException|InstantiationError|InternalError|"+

        "NegativeArraySizeException|NoSuchFieldError|Override|Process|"+
        "ProcessBuilder|SecurityManager|StringIndexOutOfBoundsException|"+
        "SuppressWarnings|TypeNotPresentException|UnknownError|"+
        "UnsatisfiedLinkError|UnsupportedClassVersionError|VerifyError|"+
        "InstantiationException|IndexOutOfBoundsException|"+
        "ArrayIndexOutOfBoundsException|CloneNotSupportedException|"+
        "NoSuchFieldException|IllegalArgumentException|NumberFormatException|"+
        "SecurityException|Void|InheritableThreadLocal|IllegalStateException|"+
        "InterruptedException|NoSuchMethodException|IllegalAccessException|"+
        "UnsupportedOperationException|Enum|StrictMath|Package|Compiler|"+
        "Readable|Runtime|StringBuilder|Math|IncompatibleClassChangeError|"+
        "NoSuchMethodError|ThreadLocal|RuntimePermission|ArithmeticException|"+
        "NullPointerException|Long|Integer|Short|Byte|Double|Number|Float|"+
        "Character|Boolean|StackTraceElement|Appendable|StringBuffer|"+
        "Iterable|ThreadGroup|Runnable|Thread|IllegalMonitorStateException|"+
        "StackOverflowError|OutOfMemoryError|VirtualMachineError|"+
        "ArrayStoreException|ClassCastException|LinkageError|"+
        "NoClassDefFoundError|ClassNotFoundException|RuntimeException|"+
        "Exception|ThreadDeath|Error|Throwable|System|ClassLoader|"+
        "Cloneable|Class|CharSequence|Comparable|String|Object|" +
        "Unit|Any|AnyVal|AnyRef|Null|ScalaObject|Singleton|Seq|Iterable|List|" +
        "Option|Array|Char|Byte|Int|Long|Nothing|" +

        "App|Application|BufferedIterator|BigDecimal|BigInt|Console|Either|" +
        "Enumeration|Equiv|Fractional|Function|IndexedSeq|Integral|Iterator|" +
        "Map|Numeric|Nil|NotNull|Ordered|Ordering|PartialFunction|PartialOrdering|" +
        "Product|Proxy|Range|Responder|Seq|Serializable|Set|Specializable|Stream|" +
        "StringContext|Symbol|Traversable|TraversableOnce|Tuple|Vector|Pair|Triple|" +

        ##OMClasses##
    );

var keywordMapper = this.createKeywordMapper({
                            "variable.language": "this",
                            "keyword": keywords,
                            "support.function": langClasses,
                            "constant.language": buildinConstants
                        }, "identifier");

    this.$rules = {
        "start" : [
            {
                token : "comment",
                regex : "\\/\\/.*$"
            },
            {
                token : "comment", // multi line comment
                regex : "\\/\\*",
                next : "comment"
            }, {
                token : "string.regexp",
                regex : "[/](?:(?:\\[(?:\\\\]|[^\\]])+\\])|(?:\\\\/|[^\\]/]))*[/]\\w*\\s*(?=[).,;]|$)"
            }, {
                token : "string",
                regex : '"""',
                next : "tstring"
            }, {
                token : "string",
                regex : '"(?=.)', // " strings can't span multiple lines
                next : "string"
            }, {
                token : "symbol.constant", // single line
                regex : "'[\\w\\d_]+"
            }, {
                token : "constant.numeric", // hex
                regex : "0[xX][0-9a-fA-F]+\\b"
            }, {
                token : "constant.numeric", // float
                regex : "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"
            }, {
                token : "constant.language.boolean",
                regex : "(?:true|false)\\b"
            }, {
                token : keywordMapper,
                // TODO: Unicode escape sequences
                // TODO: Unicode identifiers
                regex : "[a-zA-Z_$][a-zA-Z0-9_$]*\\b"
            }, {
                token : "keyword.operator",
                regex : "!|\\$|%|&|\\*|\\-\\-|\\-|\\+\\+|\\+|~|===|==|=|!=|!==|<=|>=|<<=|>>=|>>>=|<>|<|>|!|&&|\\|\\||\\?\\:|\\*=|%=|\\+=|\\-=|&=|\\^=|\\b(?:in|instanceof|new|delete|typeof|void)"
            }, {
                token : "paren.lparen",
                regex : "[[({]"
            }, {
                token : "paren.rparen",
                regex : "[\\])}]"
            }, {
                token : "text",
                regex : "\\s+"
            }
        ],
        "comment" : [
            {
                token : "comment", // closing comment
                regex : ".*?\\*\\/",
                next : "start"
            }, {
                token : "comment", // comment spanning whole line
                regex : ".+"
            }
        ],
        "string" : [
            {
                token : "escape",
                regex : '\\\\"'
            }, {
                token : "string",
                regex : '"',
                next : "start"
            }, {
                token : "string.invalid",
                regex : '[^"\\\\]*$',
                next : "start"
            }, {
                token : "string",
                regex : '[^"\\\\]+'
            }
        ],
        "tstring" : [
            {
                token : "string",
                regex : '"{3,5}',
                next : "start"
            }, {
                defaultToken : "string"
            }
        ]
    };
};

oop.inherits(OpenMOLEHighlightRules, ScalaHighlightRules);
exports.OpenMOLEHighlightRules = OpenMOLEHighlightRules;

});
