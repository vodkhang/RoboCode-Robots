//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:2.0.50727.4927
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace robocode.exception {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaClassAttribute()]
    [global::System.SerializableAttribute()]
    public partial class DisabledException : global::java.lang.Error {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId @__ctorDisabledException0;
        
        internal static global::net.sf.jni4net.jni.MethodId @__ctorDisabledException1;
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;)V")]
        public DisabledException(global::java.lang.String par0) : 
                base(((global::net.sf.jni4net.jni.JNIEnv)(null))) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            @__env.NewObject(global::robocode.exception.DisabledException.staticClass, global::robocode.exception.DisabledException.@__ctorDisabledException0, this, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0));
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()V")]
        public DisabledException() : 
                base(((global::net.sf.jni4net.jni.JNIEnv)(null))) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            @__env.NewObject(global::robocode.exception.DisabledException.staticClass, global::robocode.exception.DisabledException.@__ctorDisabledException1, this);
        }
        
        protected DisabledException(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        protected DisabledException(global::System.Runtime.Serialization.SerializationInfo info, global::System.Runtime.Serialization.StreamingContext context) : 
                base(info, context) {
        }
        
        public new static global::java.lang.Class _class {
            get {
                return global::robocode.exception.DisabledException.staticClass;
            }
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::robocode.exception.DisabledException.staticClass = @__class;
            global::robocode.exception.DisabledException.@__ctorDisabledException0 = @__env.GetMethodID(global::robocode.exception.DisabledException.staticClass, "<init>", "(Ljava/lang/String;)V");
            global::robocode.exception.DisabledException.@__ctorDisabledException1 = @__env.GetMethodID(global::robocode.exception.DisabledException.staticClass, "<init>", "()V");
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::robocode.exception.DisabledException(@__env);
            }
        }
    }
    #endregion
}
