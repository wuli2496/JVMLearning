package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ClassUtil {
    public static void parseFile(String strFile) throws IOException
    {
        class ClassAndNameTypeIndex
        {
            int class_index;
            int name_and_type_index;
        }

        class MethodHandle
        {
            int refKind;
            int refIndex;
        }

        class InvokeDynamic
        {
            int bootstrapIndex;
            int nameTypeIndex;
        }


        Map<Integer, String> utf8_info = new HashMap<>();
        Map<Integer, Integer> class_info = new HashMap<>();
        Map<Integer, ClassAndNameTypeIndex>  fieldOrMethodOrInterfaceMethodRef_info = new HashMap<>();
        Map<Integer, Integer> string_info = new HashMap<>();
        Map<Integer, Integer> integer_info = new HashMap<>();
        Map<Integer, Float> float_info = new HashMap<>();
        Map<Integer, Long> long_info = new HashMap<>();
        Map<Integer, Double> double_info = new HashMap<>();
        Map<Integer, MethodHandle> methodHandle_info = new HashMap<>();
        Map<Integer, Integer> methodType_info = new HashMap<>();
        Map<Integer, InvokeDynamic> invokeDynamic_info = new HashMap<>();


        int accessFlag;
        File file = new File(strFile);
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] data = new byte[bi.available()];
            bi.read(data);

            DataInputStream di = new DataInputStream(new ByteArrayInputStream(data));
            int magic = di.readInt();
            if (magic != 0xCAFEBABE)
            {
                System.out.println("该文件不是class文件");
                return;
            }

            int minorVersion = di.readShort();
            int majorVersion = di.readShort();
            System.out.println("jdk版本为: " + majorVersion + "." + minorVersion);
            int constant_pool_size = di.readShort();
            System.out.println("常量池个数为: " + (constant_pool_size - 1));

            for (int i = 1; i < constant_pool_size; i++)
            {
                int tag = di.read();
                switch (tag)
                {
                    case 1:
                        int len = di.readShort();
                        char[] bytes = new char[len];
                        for (int j = 0; j < len; j++)
                        {
                            bytes[j] = (char)di.read();
                        }
                        utf8_info.put(i, new String(bytes));
                        break;
                    case 7:
                        int name_index = di.readShort();
                        class_info.put(i, name_index);
                        break;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        int class_index = di.readShort();
                        int name_type_index = di.readShort();
                        fieldOrMethodOrInterfaceMethodRef_info.put(i, new ClassAndNameTypeIndex(){{this.class_index = class_index; this.name_and_type_index = name_type_index;}});
                        break;
                    case 8:
                        int string_index = di.readShort();
                        string_info.put(i, string_index);
                        break;
                    case 3:
                        int integer = di.readInt();
                        integer_info.put(i, integer);
                        break;
                    case 4:
                        float float_num = di.readFloat();
                        float_info.put(i, float_num);
                        break;
                    case 5:
                        long long_num = di.readLong();
                        long_info.put(i, long_num);
                        break;
                    case 6:
                        double double_num = di.readDouble();
                        double_info.put(i, double_num);
                        break;
                    case 15:
                        int refKind = di.readUnsignedByte();
                        int refIndex = di.readUnsignedShort();
                        methodHandle_info.put(i, new MethodHandle(){{this.refIndex = refIndex; this.refKind = refKind;}});
                        break;
                    case 16:
                        int desc_index = di.readUnsignedShort();
                        methodType_info.put(i, desc_index);
                        break;
                    case 18:
                        int bootstrap_index = di.readUnsignedByte();
                        int nameTypeIndex = di.readUnsignedShort();
                        invokeDynamic_info.put(i, new InvokeDynamic(){{this.bootstrapIndex = bootstrap_index; this.nameTypeIndex = nameTypeIndex;}});
                        break;
                }
            }

            accessFlag = di.readUnsignedShort();
            System.out.println("访问标志: " + accessFlag);

            int this_class = di.readUnsignedShort();
            System.out.println("类索引: " + this_class);
            System.out.println("类名: " + utf8_info.get(class_info.get(this_class)));

            int super_index = di.readUnsignedShort();
            System.out.println("父类索引: " + super_index);
            System.out.println("父类名: " + utf8_info.get(class_info.get(super_index)));

            int interface_count = di.readUnsignedShort();
            System.out.println("接口数: " + interface_count);
            for (int i = 0; i < interface_count; i++)
            {
                int interface_index = di.readUnsignedShort();
                System.out.println("接口名为: " + utf8_info.get(class_info.get(interface_index)));
            }

            int field_count = di.readUnsignedShort();
            System.out.println("字段数：" + field_count);
        } finally {
            bi.close();
        }
    }
}
