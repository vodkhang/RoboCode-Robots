//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:2.0.50727.4927
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace net.sf.robocode.serialization {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaInterfaceAttribute()]
    public partial interface ISerializableHelper {
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Lnet/sf/robocode/serialization/RbSerializer;Ljava/lang/Object;)I")]
        int sizeOf(global::net.sf.robocode.serialization.RbSerializer par0, global::java.lang.Object par1);
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;Ljava/lang/Obje" +
            "ct;)V")]
        void serialize(global::net.sf.robocode.serialization.RbSerializer par0, global::java.nio.ByteBuffer par1, global::java.lang.Object par2);
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;)Ljava/lang/Obj" +
            "ect;")]
        global::java.lang.Object deserialize(global::net.sf.robocode.serialization.RbSerializer par0, global::java.nio.ByteBuffer par1);
    }
    #endregion
    
    #region Component Designer generated code 
    public partial class ISerializableHelper_ {
        
        public static global::java.lang.Class _class {
            get {
                return global::net.sf.robocode.serialization.@__ISerializableHelper.staticClass;
            }
        }
    }
    #endregion
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaProxyAttribute(typeof(global::net.sf.robocode.serialization.ISerializableHelper), typeof(global::net.sf.robocode.serialization.ISerializableHelper_))]
    [global::net.sf.jni4net.attributes.ClrWrapperAttribute(typeof(global::net.sf.robocode.serialization.ISerializableHelper), typeof(global::net.sf.robocode.serialization.ISerializableHelper_))]
    internal sealed partial class @__ISerializableHelper : global::java.lang.Object, global::net.sf.robocode.serialization.ISerializableHelper {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId _sizeOf0;
        
        internal static global::net.sf.jni4net.jni.MethodId _serialize1;
        
        internal static global::net.sf.jni4net.jni.MethodId _deserialize2;
        
        private @__ISerializableHelper(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::net.sf.robocode.serialization.@__ISerializableHelper.staticClass = @__class;
            global::net.sf.robocode.serialization.@__ISerializableHelper._sizeOf0 = @__env.GetMethodID(global::net.sf.robocode.serialization.@__ISerializableHelper.staticClass, "sizeOf", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/lang/Object;)I");
            global::net.sf.robocode.serialization.@__ISerializableHelper._serialize1 = @__env.GetMethodID(global::net.sf.robocode.serialization.@__ISerializableHelper.staticClass, "serialize", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;Ljava/lang/Obje" +
                    "ct;)V");
            global::net.sf.robocode.serialization.@__ISerializableHelper._deserialize2 = @__env.GetMethodID(global::net.sf.robocode.serialization.@__ISerializableHelper.staticClass, "deserialize", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;)Ljava/lang/Obj" +
                    "ect;");
        }
        
        public int sizeOf(global::net.sf.robocode.serialization.RbSerializer par0, global::java.lang.Object par1) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            return ((int)(@__env.CallIntMethod(this, global::net.sf.robocode.serialization.@__ISerializableHelper._sizeOf0, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParFullC2J<global::java.lang.Object>(@__env, par1))));
        }
        
        public void serialize(global::net.sf.robocode.serialization.RbSerializer par0, global::java.nio.ByteBuffer par1, global::java.lang.Object par2) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            @__env.CallVoidMethod(this, global::net.sf.robocode.serialization.@__ISerializableHelper._serialize1, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1), global::net.sf.jni4net.utils.Convertor.ParFullC2J<global::java.lang.Object>(@__env, par2));
        }
        
        public global::java.lang.Object deserialize(global::net.sf.robocode.serialization.RbSerializer par0, global::java.nio.ByteBuffer par1) {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            return global::net.sf.jni4net.utils.Convertor.FullJ2C<global::java.lang.Object>(@__env, @__env.CallObjectMethodPtr(this, global::net.sf.robocode.serialization.@__ISerializableHelper._deserialize2, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0), global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par1)));
        }
        
        private static global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> @__Init(global::net.sf.jni4net.jni.JNIEnv @__env, global::java.lang.Class @__class) {
            global::System.Type @__type = typeof(__ISerializableHelper);
            global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> methods = new global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod>();
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "sizeOf", "sizeOf0", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/lang/Object;)I"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "serialize", "serialize1", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;Ljava/lang/Obje" +
                        "ct;)V"));
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "deserialize", "deserialize2", "(Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;)Ljava/lang/Obj" +
                        "ect;"));
            return methods;
        }
        
        private static int sizeOf0(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle par0, global::net.sf.jni4net.utils.JniLocalHandle par1) {
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/lang/Object;)I
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/lang/Object;)I
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            int @__return = default(int);
            try {
            global::net.sf.robocode.serialization.ISerializableHelper @__real = global::net.sf.jni4net.utils.Convertor.FullJ2C<global::net.sf.robocode.serialization.ISerializableHelper>(@__env, @__obj);
            @__return = ((int)(@__real.sizeOf(global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::net.sf.robocode.serialization.RbSerializer>(@__env, par0), global::net.sf.jni4net.utils.Convertor.FullJ2C<global::java.lang.Object>(@__env, par1))));
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
            return @__return;
        }
        
        private static void serialize1(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle par0, global::net.sf.jni4net.utils.JniLocalHandle par1, global::net.sf.jni4net.utils.JniLocalHandle par2) {
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;Ljava/lang/Object;)V
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;Ljava/lang/Object;)V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::net.sf.robocode.serialization.ISerializableHelper @__real = global::net.sf.jni4net.utils.Convertor.FullJ2C<global::net.sf.robocode.serialization.ISerializableHelper>(@__env, @__obj);
            @__real.serialize(global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::net.sf.robocode.serialization.RbSerializer>(@__env, par0), global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::java.nio.ByteBuffer>(@__env, par1), global::net.sf.jni4net.utils.Convertor.FullJ2C<global::java.lang.Object>(@__env, par2));
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        private static global::net.sf.jni4net.utils.JniHandle deserialize2(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj, global::net.sf.jni4net.utils.JniLocalHandle par0, global::net.sf.jni4net.utils.JniLocalHandle par1) {
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;)Ljava/lang/Object;
            // (Lnet/sf/robocode/serialization/RbSerializer;Ljava/nio/ByteBuffer;)Ljava/lang/Object;
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            global::net.sf.jni4net.utils.JniHandle @__return = default(global::net.sf.jni4net.utils.JniHandle);
            try {
            global::net.sf.robocode.serialization.ISerializableHelper @__real = global::net.sf.jni4net.utils.Convertor.FullJ2C<global::net.sf.robocode.serialization.ISerializableHelper>(@__env, @__obj);
            @__return = global::net.sf.jni4net.utils.Convertor.FullC2J<global::java.lang.Object>(@__env, @__real.deserialize(global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::net.sf.robocode.serialization.RbSerializer>(@__env, par0), global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::java.nio.ByteBuffer>(@__env, par1)));
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
            return @__return;
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::net.sf.robocode.serialization.@__ISerializableHelper(@__env);
            }
        }
    }
    #endregion
}
