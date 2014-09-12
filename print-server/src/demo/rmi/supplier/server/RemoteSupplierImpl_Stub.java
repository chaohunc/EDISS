// Stub class generated by rmic, do not edit.
// Contents subject to change without notice.

package demo.rmi.supplier.server;

public final class RemoteSupplierImpl_Stub
    extends java.rmi.server.RemoteStub
    implements demo.rmi.supplier.common.RemoteSupplier, java.rmi.Remote
{
    private static final long serialVersionUID = 2;
    
    private static java.lang.reflect.Method $method_browseItems_0;
    private static java.lang.reflect.Method $method_itemIDtoItem_1;
    private static java.lang.reflect.Method $method_orderItem_2;
    private static java.lang.reflect.Method $method_queryItem_3;
    
    static {
	try {
	    $method_browseItems_0 = demo.rmi.supplier.common.RemoteSupplier.class.getMethod("browseItems", new java.lang.Class[] {});
	    $method_itemIDtoItem_1 = demo.rmi.supplier.common.RemoteSupplier.class.getMethod("itemIDtoItem", new java.lang.Class[] {java.lang.String.class});
	    $method_orderItem_2 = demo.rmi.supplier.common.RemoteSupplier.class.getMethod("orderItem", new java.lang.Class[] {java.lang.String.class, int.class});
	    $method_queryItem_3 = demo.rmi.supplier.common.RemoteSupplier.class.getMethod("queryItem", new java.lang.Class[] {java.lang.String.class});
	} catch (java.lang.NoSuchMethodException e) {
	    throw new java.lang.NoSuchMethodError(
		"stub class initialization failed");
	}
    }
    
    // constructors
    public RemoteSupplierImpl_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of browseItems()
    public java.util.ArrayList browseItems()
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_browseItems_0, null, 3631823730922102655L);
	    return ((java.util.ArrayList) $result);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of itemIDtoItem(String)
    public demo.rmi.supplier.common.Item itemIDtoItem(java.lang.String $param_String_1)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_itemIDtoItem_1, new java.lang.Object[] {$param_String_1}, -926725484000082875L);
	    return ((demo.rmi.supplier.common.Item) $result);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of orderItem(String, int)
    public int orderItem(java.lang.String $param_String_1, int $param_int_2)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_orderItem_2, new java.lang.Object[] {$param_String_1, new java.lang.Integer($param_int_2)}, 4192301594738693910L);
	    return ((java.lang.Integer) $result).intValue();
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of queryItem(String)
    public java.util.ArrayList queryItem(java.lang.String $param_String_1)
	throws java.rmi.RemoteException
    {
	try {
	    Object $result = ref.invoke(this, $method_queryItem_3, new java.lang.Object[] {$param_String_1}, -5484671052303728166L);
	    return ((java.util.ArrayList) $result);
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}