package org.eclipse.scout.healthcare.shared.devices;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IDeviceLookupService extends ILookupService<String> {
}
