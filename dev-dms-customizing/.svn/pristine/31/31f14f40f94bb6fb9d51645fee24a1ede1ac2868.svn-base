package fwk.crypto.internal;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import nexcore.framework.core.crypt.internal.CryptoManager;
import nexcore.framework.core.crypt.internal.NoKeyFoundException;
import nexcore.framework.core.exception.FwkRuntimeException;
import nexcore.framework.core.util.Base64;

public class HpcCryptoManager extends CryptoManager {

    private boolean generateKey      = false;
    private IvParameterSpec iv;
    
    public void setGenerateKey(boolean generateKey){
        this.generateKey = generateKey;
    }
    
    @Override
    public void init() throws Exception {
        try {
            key = getKeySaver().load();
            iv = ((HpcFileKeySaver)getKeySaver()).getIvParameterSpec();
        } catch (NoKeyFoundException e) {
            if (generateKey){//HPC는 기본적으로 key생성을 하지 않도록 한다. 
                // generate a new one
                log.warn("Can't find a key with given keySaver and properties."
                        + " making a new one...");
                key = generateNewKey();
                getKeySaver().save(key);
            }else{
                //2015.10.13 jihooyim code inspector 점검 수정 (throw할 때 exception 변수도 인자로 넣기) 
                throw new RuntimeException("Failed to find encryption key.",e);
            }
        }
    }

    @Override
    public String encode(String source) {
        Cipher cipher = null;
        String base64 = "";
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] stringBytes = source.getBytes(encoding);
            byte[] raw = cipher.doFinal(stringBytes);
            
            base64 = Base64.encodeToString(raw, false);
            
        } catch (Exception e) {
            throw new FwkRuntimeException( "Exception occurred in processing encoding", e);
        }
        return base64; 
    }
    
    
    @Override
    public String decode(String source) {
        String result = null;
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] raw = Base64.decode(source);
            byte[] stringBytes = cipher.doFinal(raw);
            result = new String(stringBytes, encoding);
        } catch (Exception e) {
            // TODO - 이상은 - 적절한 예외 등록
            throw new FwkRuntimeException("Exception occurred in processing decoding", e);
        }
        return result;
    }
//    @Override
//    public Key generateNewKey() throws NoSuchAlgorithmException {
//        KeyGenerator keygenerator = KeyGenerator.getInstance(keyAlgorithm);
//        if (keyLength > 0) {
//            // 키 길이가 별도 지정되었음.
//            keygenerator.init(keyLength);
//        } else {
//            keygenerator.init(new SecureRandom());
//        }
//        Key key = keygenerator.generateKey();
//        return key;
//    }
}
